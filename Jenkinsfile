pipeline {
    agent any
    stages {
        stage('Build Partida') {
            steps {
                build job: 'race.partida', wait: true
            }
        }
        stage('Build') { 
            steps {
                sh 'mvn clean package'
            }
        }      
        stage('Build Image') {
            steps {
                script {
                    partida = docker.build("weeeveralex/partida:${env.BUILD_ID}", "-f Dockerfile .")
                }
            }
        }
        stage('Push Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credential') {
                        partida.push("${env.BUILD_ID}")
                        partida.push("latest")
                    }
                }
            }
        }
    }
}