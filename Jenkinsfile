pipeline {
    agent any
    triggers {
        pollSCM('H * * * *')
    }
    tools {
        maven 'maven_3_9_9'
    }
    environment {
        DOCKER_HOME = tool 'myDocker'  // Define Docker tool
        PATH = "${env.DOCKER_HOME}/bin:${env.PATH}"  // Update PATH
    }
    stages {
        stage('Initialize') {
            steps {
                script {
                    echo "Initializing build environment"
                }
            }
        }
        stage('Maven Build') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/merikid/jenkins-automation']])
                sh 'mvn clean install'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // Define repoName at the pipeline level
                    env.REPO_NAME = "jenkinslearn"

//                     // Fetch Git repository URL dynamically
//                     def gitUrl = sh(script: "git config --get remote.origin.url", returnStdout: true).trim()
//                     echo "Git Remote URL: ${gitUrl}"
//
//                     // Extract repo name dynamically
//                     env.REPO_NAME = gitUrl.tokenize('/').last().replace('.git', '')
//                     echo "Extracted Repo Name: ${env.REPO_NAME}"

                    def imageName = "merikid/${env.REPO_NAME}"
                    echo "Building Docker image: ${imageName}"

                    sh "docker build -t ${imageName} ."
                }
            }
        }
        stage('Push to Registry') {
            steps {
                script {
                    def imageName = "merikid/${env.REPO_NAME}"  // Reuse the updated repoName
                    echo "Pushing Docker image: ${imageName}"

                    sh 'docker login -u merikid -p DockerHub#987'
                    sh "docker push ${imageName}"
                }
            }
        }
    }
}
