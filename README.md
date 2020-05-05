# Rsa Mqtt Client Software

This tool enables secure communication between a sender and a receiver of messages. The sender publishes messages to a broker and the receiver, subscribes the chanel to consume the messages. The messages are encoded. 

## What is Mqtt?
MQTT is a machine-to-machine (M2M)/"Internet of Things" connectivity protocol.
[ More details ](http://mqtt.org/) 


## RSA Keypair generation

In this code tutorial, openssl was used to generate a keypair (public/private-key).

Code:

```python
# create keypair
$ openssl genrsa -out mykey.pem 2048

# Convert private Key to PKCS#8 format (so Java can read it)
$ openssl pkcs8 -topk8 -inform PEM -outform DER -in mykey.pem -out private_key.der -nocrypt

# Output public key portion in DER format (so Java can read it)
$ openssl rsa -in mykey.pem -pubout -outform DER -out public_key.der
```

## Instructions
To see how this code works, just start the project in your IDE and then run the class "receiver.java". This class just waits from incoming, encoded messages.

To send messages to your already started receiver, run the class "Sender.java". This class now sends encoded messages to the receiver.



[comment]: <> ( Originally inspired from: https://stackoverflow.com/questions/11787571/how-to-read-pem-file-to-get-private-and-public-key)

