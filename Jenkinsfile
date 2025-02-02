pipeline {
    agent any

    environment {
        SONARQUBE_URL = 'http://localhost:9000'
        SONARQUBE_TOKEN = credentials('SonarJen')  // Ensure this is set in Jenkins credentials
        MAVEN_HOME = tool name: 'Maven_3.9.9', type: 'maven' // Matches Jenkins configuration
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/mtoliver1/TestProject1.git'
            }
        }

        stage('Build') {
            steps {
                bat "${MAVEN_HOME}/bin/mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat """
                        ${MAVEN_HOME}/bin/mvn sonar:sonar \
                        -Dsonar.projectKey=TestProject1 \
                        -Dsonar.host.url=${SONARQUBE_URL} \
                        -Dsonar.branch.name=main \
                        -Dsonar.login=${SONARQUBE_TOKEN}
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 15, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
