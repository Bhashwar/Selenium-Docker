pipeline{

    agent any

    stages{

        stage('Build Jar') {
            steps {
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Build image') {
            steps {
                bat "docker build -t=neel2025/selenium ."
            }

        }

        stage('Push image') {
            steps {
                bat "docker push neel2025/selenium"
            }

        }
    }

}
