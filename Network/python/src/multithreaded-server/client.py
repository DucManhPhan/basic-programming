import socket

def Main():
    #local host
    host = '127.0.0.1'

    # define the port that we will connect
    port = 12345

    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((host, port))

    message = "Hello from client to Multithreaded server."

    while True:
        # message sent to server
        s.send(message.encode('ascii'))
        
        # message received from server
        data = s.recv(1024)

        # print the received message
        print('Received from the server: ', str(data.decode('ascii')))

        # ask the client whether they want to continue
        ans = input('\nDo you want to continue (y/n): ')
        if (ans == 'y'):
            continue
        else: 
            break

    # close the connection
    s.close()


# Main function
if __name__ == '__main__':
    Main()

