---
layout: post
title: Learn batch programming - Part 2 - network commands
bigimg: /img/image-header/ravashing-beach.jpg
tags: [batch programming]
---

In this tutorial, we will discuss about some commands that is relevant to network commands such as netstat, ping, ...

## netstat command
Display current TCP/IP network connections and protocol statistics. Netstat means ```network status```.

Syntax:

```
NETSTAT [options] [-p protocol] [interval]
```

Key
   - -a   Display All connections and listening ports.
   - -e   Display Ethernet statistics. (may be combined with -s)
   - -n   Display addresses and port numbers in Numerical form.
   - -r   Display the Routing table.
   - -o   Display the Owning process ID associated with each connection.

   - -b   Display the exe involved in creating each connection or listening port.
   - -v   Verbose - use in conjunction with -b, to display the sequence of
        components involved for all executables.

   - -p protocol
        
        Show only connections for the protocol specified; 
        can be any of: TCP, UDP, TCPv6 or UDPv6.  
        If used with the -s option then the following protocols
        can also be specified: IP, IPv6, ICMP,or ICMPv6. 

   - -s   Display per-protocol statistics.  
   
        By default, statistics are
        shown for IP, IPv6, ICMP, ICMPv6, TCP, TCPv6, UDP, and UDPv6;

        (The v6 protocols are not available under 2k and NT4)
        The -p option can be used to display just a subset of these.

   - interval     
   
        Redisplay statistics, pausing interval seconds between
        each display. (default=once only) Press CTRL+C to stop. 

The ```-b``` option will display the executable name in [] at the bottom, with the component it called on top, repeated until TCP/IP is reached. This option can be time-consuming and will fail unless you have sufficient permissions.


<br>

## ping command
Test a network connection - if successful, ping returns the ip address.

Syntax:

```
PING [options] destination_host
```


```
Options
    -w timeout     
        Timeout in milliseconds to wait for each - reply, default=4000.

    -i TTL         Time To Live.

    -v TOS         Type Of Service.

    -a             Resolve addresses to hostnames.

    -n count       Number of echo requests to send.

    -t             Ping the destination host until interrupted.
                   To see statistics and continue type Control-Break;
                   To stop type Control-C.

    -l size        Send buffer size (default=32).

    -f             Set don’t Fragment flag in 
    packet (IPv4-only).

    -r count       Record route for count hops (IPv4-only).

    -s count       Timestamp for count hops (IPv4-only).

    -j host_list   Loose source route along host_list (IPv4-only).

    -k host_list   Strict source route along host_list (IPv4-only).

destination_host  The name of the remote host
    -R             Use routing header to test reverse route also (IPv6-only).
    -S srcaddr     Source address to use.
    -4             Force using IPv4.
    -6             Force using IPv6.
```

For example:

Ping a server just once:
PING -n 1 Server64

Check if a host is reachable:
PING Server64 |find "TTL=" && ECHO MyHost found

Check if a host is not reachable:
PING Server64 |find "TTL=" || ECHO MyHost not found

Test which iSCSI IP on a specific NIC is functioning or if a specific teamed NIC is operating as it should:
Ping -S (Source IP: XXX.XXX.XXX.XXX) (Destination IP: XXX.XXX.XXX.XXX)
Ping -S 10.5.7.64  10.5.7.1

Ping a website 5 times:
PING -n 5 -w 7500 www.microsoft.com

Monitor a website (example.com) every 15 seconds:

@Echo off
Echo Logging ping responses, press CTRL-C to stop
:start
 Ping -n 1 example.com | find "TTL=" >>c:\pingtest.txt
 Echo .
 Ping -n 16 127.0.0.1>nul
goto start

<br>


