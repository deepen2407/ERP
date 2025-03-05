package com.croods.vyaparerp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableCaching
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
public class VyaparerpApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(VyaparerpApplication.class, args);
    }

    @Primary
    @Bean
    public TaskExecutor primaryTaskExecutor() {
        return new ThreadPoolTaskExecutor();
        // add necessary properties to the executor

    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(VyaparerpApplication.class);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata")); // It will set UTC timezone
//        	 Fetch the service account key JSON file contents
//        	FileInputStream serviceAccount = new FileInputStream(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "firebase-service-account.json"));

        // Initialize the app with a service account, granting admin privileges
        /*
         * FirebaseOptions options = FirebaseOptions.builder()
         * .setCredentials(GoogleCredentials.fromStream(serviceAccount)) // The database
         * URL depends on the location of the database
         * .setDatabaseUrl("https://self-checkout-a00fd-default-rtdb.firebaseio.com/")
         * .build(); if(FirebaseApp.getApps().isEmpty()) {
         * FirebaseApp.initializeApp(options); }
         */
        // As an admin, the app has access to read and write all data, regardless of Security Rules
        /*
         * DatabaseReference ref = FirebaseDatabase.getInstance()
         * .getReference("restricted_access/secret_document");
         * ref.addListenerForSingleValueEvent(new ValueEventListener() {
         *
         * @Override public void onDataChange(DataSnapshot dataSnapshot) { Object
         * document = dataSnapshot.getValue(); System.out.println(document); }
         *
         * @Override public void onCancelled(DatabaseError error) { } });
         */
    }
}
