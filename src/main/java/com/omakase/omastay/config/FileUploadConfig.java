package com.omakase.omastay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class FileUploadConfig {
    
    @Bean
    public Storage storage() throws Exception {

        ClassPathResource resource = new ClassPathResource("artful-mystery-436200-n4-154c84a846c8.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());
		String projectId = "artful-mystery-436200-n4";
        return StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(credentials)
                .build()
                .getService();
    }

}
