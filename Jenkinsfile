pipeline {
    agent any

    stages {
        stage('GIT STAGE') {
            steps {
                echo 'BRANCHE ZAYNEB';
                git branch :'zayneb', url: 'https://github.com/MariemMannai/DevopsProject.git'
            }
        }
        stage ('MVN CLEAN STAGE') {
            steps {
                sh 'mvn clean' 
            }
        }
        stage ('MVN COMPILE STAGE') {
            steps {
                sh 'mvn compile' 
            }
        }
        stage("SONARQUBE STAGE") {
             steps {  
      
           sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.1.20:9000 -Dsonar.login=admin -Dsonar.password=sonar'
        }}
        
        stage('TEST STAGE') {
        steps{
            sh'mvn test'
        }
        post {
            always {
            junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
        }
        }
         
        }
        
        stage('NEXUS STAGE') {
        steps{
            sh'mvn deploy '
           
        }
     
}
stage('DOCKER BUILD IMG STAGE '){
                steps{
                    script{
                        sh 'docker build -t achat-1.0-s7 .'
                    }
                   
                }
               
            }
    stage('DOCKER PUSH IMG STAGE '){
                steps{
                    script{
                        withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                        sh 'docker login -u bfzayneb -p ${dockerhubpwd}'
                             }
                        sh 'docker tag  achat-1.0-s7 bfzayneb/achat-1.0-s7:latest'    
                        sh 'docker push bfzayneb/achat-1.0-s7'    
                    }
                   
                }
               
            }
            stage('DOCKER COMPOSE STAGE') {

                          steps {
                               sh 'docker-compose up -d '
                                 }  }
        
        stage('Email') {
        steps{
            mail bcc: '',
            body: 'en cours d execution ',
            cc: '', from: '',
            replyTo: '', subject: 'c est terminé',
            to: 'zayneb.benfekih@esprit.tn'
        }
        }

       

}
}