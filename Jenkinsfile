pipeline {
    
    environment { 

        registry = "rouamaknin/spring-achat" 

        registryCredential = 'dockerId' 

        dockerImage = '' 

    }
   
    agent any

    stages {
        stage('GIT') {
            steps {
                
                 git branch :'roua', url: 'https://github.com/MariemMannai/DevopsProject.git'
            }
        }
        stage ('CLEANNING MVN') {
            steps {
                sh 'mvn clean package '
            }
        } 
 
         stage ('COMPILATION MVN') {
            steps {
                sh 'mvn compile'
            }
        }
        
        stage('MVN SONAREQUBE') {
            steps {
                sh'mvn sonar:sonar -Dsonar.login=1fde4dd08ae04e50897b4a4457a235582f1c0b67'
                }
           
        }
        stage ('MVN test') {
            steps {
                sh  'mvn test'
            }
        }
        stage ('MVN NEXUS') {
            steps {
                sh  'mvn clean deploy'
            }
        }
        
     

        

        stage('Building image') { 

            steps { 

                script { 

                    dockerImage = docker.build registry + ":$BUILD_NUMBER" 

                }

            } 

        }

        stage('Deploy image') { 

            steps { 

                script { 

                    docker.withRegistry( '', registryCredential ) { 

                        dockerImage.push() 

                    }

                } 

            }

        }
        
        stage ('DOCKER COMPOSE') {
            steps {
                sh ' docker-compose up '
              
            }
        }
        
         stage('Mailing') {
        steps{
            mail bcc: '',
            body: 'Still going ',
            cc: '', from: '',
            replyTo: '', subject: 'hello,your pepeline is doing something',
            to: 'roua.maknin@esprit.tn'
        }
        }
    }}
        
