#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('test') {
            steps {
                script {
                    echo "Testing the application..."
                }
            }
        }
        stage('build') {
            steps {
                script {
                    echo "Building the application..."
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    // def dockerCmd = 'docker run -p 3080:3080 -d umeshsurya01/demo-app:latest'
                    echo 'deploying docker images to ec2'
                    def dockerComposeCmd = "docker-compose -f docker-compose.yaml up --detach"
                    sshagent(['ec2-server-key']){
                        sh "scp docker-compose.yaml ec2-user@3.109.217.233:/home/ec2-user"
                        sh " ssh -o StrictHostKeyChecking=no ec2-user@3.109.217.233 ${dockerCmd}"
                    }
                }
            }
        }
    }
}
