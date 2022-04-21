package com.metaverse.station.back.utils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.DeleteObjectsResult.DeletedObject;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.metaverse.station.back.domain.images.ImagesRepository;
import com.metaverse.station.back.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Scheduler {

    private final AmazonS3Client amazonS3Client;
    private final ImagesRepository imagesRepository;
    private final UserRepository userRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    @Scheduled(initialDelay = 86400000, fixedDelay = 86400000)
    public void s3DeleteObjectsScheduler() {

        List<String> postImageList = imagesRepository.getImage_pathList();
        List<String> profileImageList = userRepository.getProfileImageList();

        deleteS3Objects(postImageList,"static/");
        deleteS3Objects(profileImageList,"profileImage/");
    }

    public void deleteS3Objects(List<String> fileList,String path){

        List<String> handledList = new ArrayList<>();

        fileList.forEach(file ->
        {
            if (file.contains("/") && (file.lastIndexOf("/")+1 != file.length())) {
                file = path.concat(file.substring(file.lastIndexOf("/")+1));
            }
            else {
                file = path.concat(file);
            }
            handledList.add(file);
        });

        String fileName;
        List<String> S3Images = new ArrayList<>();
        ObjectListing objectListing = amazonS3Client.listObjects(bucket,path);
        for (S3ObjectSummary s : objectListing.getObjectSummaries()) {
            fileName = s.getKey();
            if(fileName.lastIndexOf("/")+1 != fileName.length()) {
                S3Images.add(fileName);
            }
        }

        S3Images.removeAll(handledList);
        if(!S3Images.isEmpty() ){
            DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucket);

            List<KeyVersion> keyList = new ArrayList<KeyVersion>();

            for (String key : S3Images) {
                keyList.add(new KeyVersion(key));
            }

            deleteObjectsRequest.setKeys(keyList);
            amazonS3Client.deleteObjects(deleteObjectsRequest).getDeletedObjects().forEach(deletedObject -> {
                log.info("delete unused file : " + deletedObject.getKey());
            });
        }
        else {
            log.info(path + ": 삭제할 파일이 없습니다.");
        }

    }
}
