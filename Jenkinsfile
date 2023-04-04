#!/usr/bin/env groovy

def gv

pipeline {
    agent any
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    echo "building jar"
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
                    dockerPUSH(env.IMAGE_NAME)
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
                        sh " ssh -o StrictHostKeyChecking=no ec2-user@3.109.217.233 ${dockerCmd}"
                }
            }
        }
    }   
}
