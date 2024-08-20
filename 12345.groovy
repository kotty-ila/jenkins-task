pipeline {
    agent any

    environment {
        // Define environment variables here
        // Example: MY_ENV_VAR = 'value'
    }

    tools {
        // Define tools here, e.g., JDK, Maven
        // Example: jdk 'JDK 11'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout source code from version control
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Example build step, can vary depending on the build tool
                script {
                    echo 'Building the project...'
                    sh './gradlew build'
                }
            }
        }

        stage('Test') {
            steps {
                // Run tests and publish test results
                script {
                    echo 'Running tests...'
                    sh './gradlew test'
                }
            }
            post {
                always {
                    junit '**/build/test-results/test/*.xml'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo 'Deploying the application...'
                    // Example deployment command
                    sh './deploy.sh'
                }
            }
        }

        stage('Cleanup') {
            steps {
                script {
                    echo 'Cleaning up...'
                    // Perform cleanup actions if needed
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded!'
 
