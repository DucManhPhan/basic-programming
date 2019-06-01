import socket

from _thread import *
import threading


print_lock = threading.Lock()

def threaded(c):
    while True:
        # data received from client
        data = c.recv(1024)
        if not data:
            print("Bye")

            # lock released on exit
            print_lock.release()
            break
        
        #reverse the given string from client
        data = data[::-1]

        # send back reversed string to client
        c.send(data)

    #connection closed
    c.closed()

def Main():
    host = ""

    # reverse a port on our computer
    port = 12345
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((host, port))
    print("socket binded to post", port)

    s.listen(5)
    print("Socket is listening.")

    # Wait until client wants to exit
    while True:

        # establish connection with client
        conn, addr = s.accept()

        # lock acquired by client
        print_lock.acquire()
        print('Connected to: ', addr[0], ':', addr[1])

        # Start a new thread and return its identifier
        start_new_thread(threaded, (conn,))

    s.close()

if __name__ == '__main__':
    Main()
