pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the repository
                checkout scm
            }
        }
        
        stage('Build and Test') {
            steps {
                // Build and run tests here
                bat 'mvn clean test' // Replace with your build and test commands
            }
        }
    }
}


