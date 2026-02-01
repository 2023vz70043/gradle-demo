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
                sh 'gradle clean build'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    sh """
                    $SONAR_HOME/bin/sonar-scanner \
                      -Dsonar.projectKey=gradle-demo \
                      -Dsonar.projectName=gradle-demo \
                      -Dsonar.sources=src \
                      -Dsonar.java.binaries=build/classes \
                      -Dsonar.coverage.jacoco.xmlReportPaths=build/reports/jacoco/test/jacocoTestReport.xml
                    """
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

