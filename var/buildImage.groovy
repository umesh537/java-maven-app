#!/usr/bin/env groovy


def call(){
    echo "building the docker image"
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USER', passwordVariable: 'PASS')]){
        sh 'docker build -t umeshsurya01/demo-app:jma-3.0 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push umeshsurya01/demo-app:jma-3.0'
    }
}