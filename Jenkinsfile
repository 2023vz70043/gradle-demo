pipeline {
    agent any

    environment {
        SONAR_HOME = tool 'SonarScanner'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    withCredentials([
                        string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')
                    ]) {
                        sh """
                            \$SONAR_HOME/bin/sonar-scanner \
                              -Dsonar.projectKey=gradle-demo \
                              -Dsonar.sources=src \
                              -Dsonar.java.binaries=build/classes \
                              -Dsonar.token=\$SONAR_TOKEN
                        """
                    }
                }
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }

    }
}

