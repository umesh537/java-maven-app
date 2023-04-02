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
                    def dockerCmd = 'docker run -p 3090:3090 -d umeshsurya01/demo-app:latest'
                    sshagent(['ec2-server-key']){
                        sh " ssh -o StrictHostKeyChecking=no ec2-user@3.109.217.233 ${dockerCmd}"
                    }
                }
            }
        }
    }
}
