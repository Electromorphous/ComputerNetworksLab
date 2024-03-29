set ns [new Simulator]
set tf [open trace.tr w]
$ns trace-all $tf
set nf [open nam.nam w]
$ns namtrace-all $nf

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

$ns duplex-link $n0 $n5 100Mb 1ms DropTail
$ns duplex-link $n1 $n5 100Mb 1ms DropTail
$ns duplex-link $n2 $n5 100Mb 1ms DropTail
$ns duplex-link $n3 $n5 100Mb 1ms DropTail
$ns duplex-link $n4 $n5 1Mb 1ms DropTail

$ns queue-limit $n0 $n5 4
$ns queue-limit $n2 $n5 3
$ns queue-limit $n5 $n4 2

set p0 [new Agent/Ping]
$ns attach-agent $n0 $p0
$p0 set packetSize_ 50000
$p0 set interval_ 0.0001

set p1 [new Agent/Ping]
$ns attach-agent $n1 $p1

set p2 [new Agent/Ping]
$ns attach-agent $n2 $p2
$p2 set packetSize_ 30000
$p2 set interval_ 0.00001

set p3 [new Agent/Ping]
$ns attach-agent $n3 $p3

set p4 [new Agent/Ping]
$ns attach-agent $n4 $p4

$ns connect $p0 $p4
$ns connect $p2 $p3

Agent/Ping instproc recv {from rtt} {
    $self instvar node_
    puts "Node [$node_ id] received reply from $from with $rtt ms RTT"
}

proc finish {} {
    global ns nf tf
    $ns flush-trace
    close $nf
    close $tf
    exec nam nam.nam
    exit 0
}

$ns at 0.1 "$p0 send"
$ns at 0.2 "$p0 send"
$ns at 0.3 "$p0 send"
$ns at 0.4 "$p0 send"
$ns at 0.5 "$p0 send"
$ns at 0.6 "$p0 send"
$ns at 0.7 "$p0 send"
$ns at 0.8 "$p0 send"
$ns at 0.9 "$p0 send"
$ns at 1.0 "$p0 send"
$ns at 1.1 "$p0 send"
$ns at 1.2 "$p0 send"
$ns at 1.3 "$p0 send"
$ns at 1.4 "$p0 send"
$ns at 1.5 "$p0 send"
$ns at 1.6 "$p0 send"
$ns at 1.7 "$p0 send"
$ns at 1.8 "$p0 send"
$ns at 1.9 "$p0 send"
$ns at 2.0 "$p0 send"
$ns at 2.1 "$p0 send"
$ns at 2.2 "$p0 send"
$ns at 2.3 "$p0 send"
$ns at 2.4 "$p0 send"
$ns at 2.5 "$p0 send"
$ns at 2.6 "$p0 send"
$ns at 2.7 "$p0 send"
$ns at 2.8 "$p0 send"
$ns at 2.9 "$p0 send"

$ns at 0.1 "$p2 send"
$ns at 0.2 "$p2 send"
$ns at 0.3 "$p2 send"
$ns at 0.4 "$p2 send"
$ns at 0.5 "$p2 send"
$ns at 0.6 "$p2 send"
$ns at 0.7 "$p2 send"
$ns at 0.8 "$p2 send"
$ns at 0.9 "$p2 send"
$ns at 1.0 "$p2 send"
$ns at 1.1 "$p2 send"
$ns at 1.2 "$p2 send"
$ns at 1.3 "$p2 send"
$ns at 1.4 "$p2 send"
$ns at 1.5 "$p2 send"
$ns at 1.6 "$p2 send"
$ns at 1.7 "$p2 send"
$ns at 1.8 "$p2 send"
$ns at 1.9 "$p2 send"
$ns at 2.0 "$p2 send"
$ns at 2.1 "$p2 send"
$ns at 2.2 "$p2 send"
$ns at 2.3 "$p2 send"
$ns at 2.4 "$p2 send"
$ns at 2.5 "$p2 send"
$ns at 2.6 "$p2 send"
$ns at 2.7 "$p2 send"
$ns at 2.8 "$p2 send"
$ns at 2.9 "$p2 send"

$ns at 3.0 "finish"
$ns run
