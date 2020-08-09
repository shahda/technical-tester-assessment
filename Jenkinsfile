def mvnHome = '/var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/Maven.3.0.5'
def cucumberHtmlReportPath = 'target/cucumber-reports'

pipeline {
    agent any
    options {
        ansiColor('xterm')
    }
    parameters {
        choice(
                name: 'TAGS',
                choices: ['All','@RunAllGetScenarios','@RunAllPostScenarios'],
                description: 'Choose a tag to run the corresponding tests'
        )

        choice(
                name: 'environment',
                choices: [ 'local','SIT','UAT'],
                description: 'Choose the target environment'
        ))
    }
    stages {
        stage('Prepare Workspace and checkout') {
            steps {
                cleanWs()
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh "'${mvnHome}/bin/mvn' clean compile"
            }
        }

        stage('Run Tests and Generate reports') {
            steps {

                script {
                    if ("${params.TAGS}" == 'All') {
                        sh "'${mvnHome}/bin/mvn' verify -DtargetEnv=${params.environment}"
                    } else {
                        sh "'${mvnHome}/bin/mvn' verify -DtargetEnv=${params.environment} -Dcucumber.options='--tags ${params.TAGS}'"
                    }
                }
            }
        }
    }

    post {
        always {
            publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: "${cucumberHtmlReportPath}",
                    reportFiles: 'report.html',
                    reportName: 'AutomationTestReport',
                    reportTitles: 'Automation-test-reports'
            ])
        }
    }
}