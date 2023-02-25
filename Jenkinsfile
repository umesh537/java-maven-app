
pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage("init"){
            steps{
                script{
                    gv = load "srcipt.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    gv.buildImage()
                    }
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }   
}
