package com.lilium.tutorial;

import com.lilium.tutorial.repository.DistributedRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = DistributedRepositoryImpl.class)
public class TutorialConfiguration {}
