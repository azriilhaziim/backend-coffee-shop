pipeline {
    agent any

    tools {
        // Replace with the names configured in Jenkins
        maven 'Mvn 3.9'
        jdk 'JDK 17'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building the backend application...'
                sh 'mvn clean install'
            }
        }

        stage('Unit Test') {
            steps {
                echo 'Running unit tests...'
                // The mvn clean install command in the build stage already runs unit tests
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                withSonarQubeEnv('SonarQube Local') { // Replace with your SonarQube server name
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                echo 'Deploying to local Tomcat server...'
                // Copies the generated WAR file to the specified Tomcat webapps directory
                sh 'cp target/ws-0.0.1-SNAPSHOT.war C:/Users/azriil.bin.a.haziim/Videos/dev assemetn/apache-tomcat-9.0.96/webapps/'
            }
        }
    }
}