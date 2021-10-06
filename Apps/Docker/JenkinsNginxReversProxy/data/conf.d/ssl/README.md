# How to create private SSL certificates MacOS/Linux


## Create CA Root Certificate

Open terminal and run the following commands

```
~mkdir CA
```
```
~cd CA
```
```
~/CA openssl genrsa -des3 -out CA.key 4096
```
* Enter your pass phrase. Do not forget you will need later
* You have now created your CA.key(private) ~/CA directory
* To create the root certifiate run the following commands

```
~/CA openssl req -x509 -new -nodes -key CA.key -sha256 -days 3650 -out CA.pem
```
* You have now generated the the root certificate CA.pem

## Root Certificate on MacOS(client)
```
~/CA sudo security add-trusted-cert -d -r trustRoot -k "/Library/Keychains/System.keychain" CA.pem
```


## Create SSL Certificate Request
* We need to generate CA key signed certificate for web servers
* But first we must generate CSR(Certificate Signing Request)
* Create another private key. Replace your_web_server with what ever web server you want to enable SSL for.

```
~/CA openssl genrsa -out your_web_server.local.key 4096
```
* Now generate the CSR file(your_web_server.local.csr)

```
~/CA openssl req -new -key your_web_server.local.key -out your_web_server.local.csr
```

## Create SSL Certificate
* You will need CA private key, CA certificate and CSR file to generate an SSL certificate.
* Create your_web_server.local.ext file for configuration with the following content on the remote host at ~/CA.

```
authorityKeyIdentifier=keyid,issuer
basicConstraints=CA:FALSE
keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
subjectAltName = @alt_names
[alt_names]
DNS.1 = your_web_server
```

* Now run the following command

```
~/CA openssl x509 -req -in your_web_server.local.csr -CA CA.pem
-CAkey CA.key -CAcreateserial -out your_web_server.local.crt
-days 365 -sha256 -extfile your_web_server.local.ext
```

* We have now generated your_web_server.local.crt which will be used in web server

## What files do you place in the directory ssl?
* your_web_server.local.crt
* your_web_server.local.key

## Acknowledgments

I do not take credit for any of this information. All information was directly pulled from a blog.<br /> Below you will find the link to the blog that I referenced.  
* [Hayato Iriumi](https://hayato-iriumi.net/2020/07/25/how-to-create-private-ssl-certificate-authority/)
<br />
