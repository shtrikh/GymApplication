pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the repository
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                // Build the project using Maven
                bat 'mvn clean package' // Adjust the Maven command as needed
            }
        }
        
        stage('Run Tests') {
            steps {
                // Run tests using Maven and JUnit
                bat 'mvn test' // Adjust the Maven command as needed
            }
        }
        
        stage('Archive Artifacts') {
            steps {
                // Archive the built JAR file as an artifact
                archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
            }
        }
    }
    
    post {
        success {
            // If the pipeline is successful, notify and perform further actions
            echo 'Pipeline successful'
            // You can add more actions here, such as deploying or notifying
        }
        failure {
            // If the pipeline fails, notify and perform actions
            echo 'Pipeline failed'
            // You can add more actions here, such as notifications or cleanup
        }
    }
}




