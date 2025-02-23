pipeline {
    agent any

    stages {
        stage('Build Jar') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build image') {
            steps {
                bat 'docker build -t=neel2025/selenium:latest .'
            }
        }

        stage('Push image') {
            environment {
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps {
                // Use password-stdin for secure login
                bat 'echo %DOCKER_HUB_PSW% | docker login -u %DOCKER_HUB_USR% --password-stdin'
                bat 'docker push neel2025/selenium:latest'
                // Correctly tag using Groovy interpolation for BUILD_NUMBER
                bat "docker tag neel2025/selenium:latest neel2025/selenium:${env.BUILD_NUMBER}"
                bat "docker push neel2025/selenium:${env.BUILD_NUMBER}"
            }
        }
    }

    post {
        always {
            bat "docker logout"
        }
    }
}
