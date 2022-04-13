# IDEA EDU Course ...

Implemented in the Java <b>Core</b> Track of hyperskill.org's JetBrain Academy.

Purpose of doing this project, is getting familiar with the fascinating and more and more relevant
blockchain stuff. Moreover, generic programming, streaming, multithreading, hashing and cryptography 
topics are investigated.

## Repository Contents

Beside the sources of main project tasks (6 stages), all relevant Topic-associated development tasks of the academy 
course path are included.

## Program description

The application runs without user input and must be configured by setting constants in the BlockchainConfig-class.
In particular the desired blockchain length and the block data mode (select between Chat-messages and bitcoin-simulating
transactions) should be verified and set.

After starting the program it looks for a serialized blockchain under the configured path, deserializes and validates
it and either clears the chain or continues block creation until the given goal is reached. As soon as miners start
competing block creations, the Controller starts client threads who asynchronously and send randomly timed and generated
RSA-signed chat messages or transactions based on circulating bitcoins (called VC) - depending on the chosen mode.
Each newly created block takes all the client data queued during creation of the previous block as block data.

The creation time and complexity is self-balanced: If creation time is below or above some configurable second range,
the creation complexity is increased by requiring the miners to provide a SHA256 hash for the block's data with one more
leading zero. this is achieved by integrating a random Integer number (the magic number) in the block's string representation
that is cryptographically hashed..

Enjoy!

## Project completion

Project was completed on 07.04.22.

## Progress

22.03.22 Project started. IDEA-setup and first repo.

23.03.22 Stage 1 completed, basic OOD according to MVC with BlockFactory

26.03.22 Stage 2 completed, Factory Method pattern, Serialization added

29.03.22 Stage 3 completed, block creation in thread pool with load balancing

02.04.22 Stage 4 completed, asynchronous message queue with posting client threads, chat history stored as block data in blockchain

05.04.22 Stage 5 completed, creating RSA keypair per Chat user, signing and verifying messages, unique id

06.04.22 Stage 6 completed, blockchain can now run in transaction, bitcoin mode OR chat mode.