# Project : Just on time

Autors : Kelian Sebaici, Théophile Zenou-Truchot

This project aims to deliver an app for people in need of help, and also for people wanting to help those in need. It delivers an application with a graphical interface. This app runs on Java 17 or above and requires Maven :

If Maven is not installed on your machine, use the following commands to install it (Linux only) :

```bash
mkdir -p ~/bin
cd ~/bin
wget https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz -O maven.tar.gz
tar xf maven.tar.gz
echo 'export PATH=~/bin/apache-maven-3.9.5/bin:$PATH' >> ~/.bashrc
source ~/.bashrc
```

With the current version of this project you will be able to :

- Sign up as a person needing help, a person willing to help someone in need or as a person checking if the missions are conform. The three types of user are called :
  - Needer
  - Helper
  - Validator
- Sign in if you have already created an account.
- Depending on your role, you will be able to create a mission with a date and a description, set a mission as accepted for it to be showed to a "Helper", propose help for a mission.

Please notice that the continuous integration appears as not totally working on GitHub because it is required to connect use the Forticlient VPN connected to the INSA server but all the tests will perfectly run on your own machine if you use the VPN.

This organisation of the application's developpement was made on Jira
