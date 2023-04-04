#!/usr/bin/env groovy

@Library('jenkins-shared-library')
def gv

pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    environment{
        IMAGE_NAME = 'umeshsurya01/demo-app:jma-3.0'
    }
    stages {
        stage("build app") {
            steps {
                script {
                    echo "building application jar"
                    buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building docker image"
                    buildImage(env.IMAGE_NAME)
                    dockerLogin()
                    dockerPush(env.IMAGE_NAME)
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo 'deploying docker images to ec2'
                    def dockerComposeCmd = "docker-compose -f docker-compose.yaml up --detach"
                    sshagent(['ec2-server-key']){
                        sh "scp docker-compose.yaml ec2-user@3.109.217.233:/home/ec2-user"
                        sh " ssh -o StrictHostKeyChecking=no ec2-user@3.109.217.233 ${dockerComposeCmd}"
                    }
                }
            }
        } 
    }  
}
