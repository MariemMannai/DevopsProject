pipeline {
      environment { 

        registry = "ounich/achat-back" 

        registryCredential = 'dockerhub_id' 

        dockerImage = '' 

    }
    agent any

    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling...';
                git branch: 'chayma',
                url : 'https://github.com/OuniChayma/devopsProject.git'

              
            }
        }
        stage('MNV CLEAN') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('MVN COMPILE') {
            steps {
                sh 'mvn compile'
            }
        }
       
        stage('MVN SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar  -Dsonar.login=admin -Dsonar.password=chayma1998chayma'
            }
        }
    
          stage('TEST ') {
            steps {
                sh 'mvn test'
            }
        }
          stage('Nexus') {
            steps {
sh 'mvn deploy:deploy-file -DgroupId=tn.esprit.rh -DartifactId=achat -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://192.168.1.18:8081/repository/maven-releases/ -Dfile=target/achat-1.0.jar'
            }
        }
             stage('Building our image') { 

            steps { 

                script { 

                    dockerImage = docker.build registry + ":$BUILD_NUMBER" 

                }

            } 

        }

        stage('Deploy our image') { 

            steps { 

                script { 

                    docker.withRegistry( '', registryCredential ) { 

                        dockerImage.push() 

                    }

                } 

            }

        } 

        stage('Cleaning up') { 

            steps { 

                sh "docker rmi $registry:$BUILD_NUMBER" 

            }

        } 
       stage('DOCKER COMPOSE') { 

            steps { 

                sh "docker-compose up -d -DrepositoryId=deploymentRepo -Durl=http://192.168.1.18:8081/repository/maven-releases/ -Dfile=target/achat-1.0.jar" 

            }

        }  
    }

}