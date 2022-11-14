pipeline {
    agent any

    stages {
        stage('GIT STAGE') {
            steps {
                echo 'STARTING PROJECT .....';
                 git branch :'mariem', url: 'https://github.com/MariemMannai/DevopsProject.git'
            }
        }
        stage ('MAVEN CLEAN STAGE ') {
            steps {
                sh 'mvn clean '
            }
        } 
 
         stage ('MAVEN COMPILE STAGE') {
            steps {
                sh 'mvn compile'
            }
        }
          stage ('MAVEN PACKAGE STAGE') {
            steps {
                sh 'mvn package '
            }
        }

        
       
        
           
                 stage('SONARQUBE STAGE') {
            steps {
              withSonarQubeEnv('test') {
                        sh 'mvn  sonar:sonar'
        }
                
            }
                    
                }
                 stage('MOCKITO/JUNIT STAGE') {
        steps{
            sh'mvn test'
        }
       
        }
          stage ('MAVEN NEXUS STAGE') {
            steps {
                sh  'mvn clean deploy -Dmaven.test.skip=true '
            }
        }

    
       
 stage('BUILDING IMAGE STAGE ') { 

            steps { 

                script { 
                   
                   sh  'docker build -t mariemmannai/achat-back:$BUILD_NUMBER .'

                   
                }

            } 

        }
     stage('DEPLOY IMAGE STAGE') { 

            steps { 

                script { 

                  	withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
        	sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
          sh 'docker push mariemmannai/achat-back:$BUILD_NUMBER '

                } 

            }

        } 
       
}  

        stage ('DOCKER COMPOSE STAGE') {
            steps {
                sh ' docker-compose up '
              
            }
        } 
 
         
       
    }
       
}

