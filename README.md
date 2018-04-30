# Barclays - Baggage Management System


# Problem Statement:
Need to implement a system that will route bags to their flights or the proper baggage claim with a minimal time.  The input contains the nodes with the duration required to travel the bags, bag details and flight details. The output is the optimal routing to get bags to their destinations.  Bags with a flight id of “ARRIVAL” are routed to Baggage Claim.


# Description
The above project is an implementation of airport baggage management system. It uses dijkstra shortest path algorithm to figure out the shortest path from the network from where baggage can be transmitted. 

Input are provided in a form of XML where each section are read in BaggageXMLParser class. The output of this class (BaggageInfo) is then forwarded to the BaggageEngine. BaggageEngine is capable of computing the optimal duration and path. This process is done for all Bags.

Exceptions are handled using BusinessException with proper error codes. Logging mechanism is also implemented. Unit test are performed for critical business methods.

TO run the application, Run MainApplication.java


# Output:
Baggage ID: 0001 Route: [ Concourse_A_Ticketing A5 A1] Duration : 11.0

Baggage ID: 0002 Route: [ A5 A1 A2 A3 A4] Duration : 9.0
