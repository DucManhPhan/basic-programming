# In order to run each file in this project, we need to open cmd, then, type name_file.py in cmd.
import socket

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

hostname = "localhost"

ipAddress = socket.gethostbyname(hostname)
print("IP Address of localhost is: {}".format(ipAddress))

port = 9999

s.bind((ipAddress, port))

print("Waiting for connection ...")
s.listen(5)

while True:
    conn, addr = s.accept()
    print("Got connection from ", addr)
    conn.send(b'Server saying Hi')   # We have to transfer data by using binary mode, not using string.
    conn.close()


input("Press Enter to continue...")
