# New-Territories-Cycle-Track-Navigation-System

***
### 1. Apply Sending Email Config:
###### JDK java.security
D:\Program Files\Java\jdk-11.0.12\conf\security\java.security
Delete ~~SSLv3, TLSv1, TLSv1.1~~ in jdk.tls.disabledAlgorithms
```
jdk.tls.disabledAlgorithms=RC4, DES, MD5withRSA, \
    DH keySize < 1024, EC keySize < 224, 3DES_EDE_CBC, anon, NULL, \
    include jdk.disabled.namedCurves
```

###### Google account security
Sign in and follow this link: 
<https://myaccount.google.com/lesssecureapps>  
Change "Allow less secure apps: OFF" to "Allow less secure apps: ON"

### 2. Release version
###### \release\SmartNavigationSystem.jar
#### 2.1 enter New-Territories-Cycle-Track-Navigation-System
#### 2.2 enter "java -jar release\SmartNavigationSystem.jar" in cmd
- use your own route to run this jar file

