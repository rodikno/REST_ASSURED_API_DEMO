pipeline {
    agent any
    triggers {
        pollSCM('10 * * * *')
    }
    stages {
        stage('Code Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit 'target/surefire-reports/junitreports/*.xml'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/surefire-reports/junitreports/*.xml'
        }
    }
}