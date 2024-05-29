pipeline {
    agent any
    stages {
        stage('Build Partida') {
            steps {
                build job: 'race.partida', wait: true
            }
        }
        stage('Build Redis') {
            steps {
                build job: 'race.redis', wait: true
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
        stage('Deploy on k8s') {
            steps {
                withCredentials([string(credentialsId: 'minikube_credentials', variable: 'api_token')]) {
                    script {
                        try {
                            sh 'curl -k https://host.docker.internal:61718/healthz'
                        } catch (Exception e) {
                            error "Cannot connect to Kubernetes API server"
                        }
                        sh 'kubectl --token $api_token --server https://host.docker.internal:61718 --insecure-skip-tls-verify=true apply -f ./k8s/deployment.yaml --validate=false'
                        sh 'kubectl --token $api_token --server https://host.docker.internal:61718 --insecure-skip-tls-verify=true apply -f ./k8s/service.yaml --validate=false'
                        sh 'kubectl --token $api_token --server https://host.docker.internal:61718 --insecure-skip-tls-verify=true apply -f ./k8s/configmap.yaml --validate=false'
                    }
                }
            }
        }
    }
    post {
        always {
            echo 'Pipeline finished.'
        }
        failure {
            echo 'Redis deployment failed.'
        }
    }
}
