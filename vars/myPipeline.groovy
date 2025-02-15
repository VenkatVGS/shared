def call() {
    pipeline {
        agent any
        stages {
            stage('Git Checkout') {
                steps {
                    script {
                        git branch: 'node-dev', url: 'https://github.com/VenkatVGS/multi-branch.git' //project-repo
                    }
                }
            }
      // stage ('Build') {
      //   steps {
      //     script{
      //       def mvnHome = tool name: 'maven3', type: 'maven'
      //       sh "${mvnHome}/bin/mvn clean package"
      //         sh 'mv target/onlinebookstore*.war target/mybook.war'
      //         // sh "mvn clean package"
      //         // sh "mv target/*.war target/mybook.war"
      //     }
      //   }
      // }
            stage ('Build') {
                steps {
                    script {
                        sh 'npm install'
                    }
                }
            }
            // stage ('SonarQube Analysis') {
            //     steps {
            //         script { 
            //             def scannerHome = tool 'sonarscanner4'
            //             withSonarQubeEnv('sonar-pro') {
            //                 sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=node.js-app"
            //             }
            //         }
            //     }
            // }
            stage('Docker Build Images') {
                steps {
                    script {
                        sh 'docker build -t venkateshvgs/multi:v1 .'
                        sh 'docker images'
                        sh 'docker ps'
                    }
                }
            }
            // stage('Docker Push') {
            //     steps {
            //         script {
            //             withCredentials([string(credentialsId: 'dockerPass', variable: 'dockerPassword')]) {
            //                 sh "docker login -u naresh2603 -p ${dockerPassword}"
            //                 sh 'docker push naresh2603/multi:v2'
            //                 sh 'docker rmi naresh2603/multi:v2'
            //             }
            //         }
            //     }
            // }
            // stage('Deploy on k8s') {
            //     steps {
            //         script {
            //             withKubeCredentials(kubectlCredentials: [[ credentialsId: 'kubernetes', namespace: 'ms' ]]) {
            //                 sh 'kubectl apply -f kube.yaml'
            //                 sh 'kubectl get pods -o wide'
            //             }
            //         }
            //     }
            // }
        }
    }
}
