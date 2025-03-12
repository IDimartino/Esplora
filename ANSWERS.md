1. If the address list contains more than one hundred addresses, which solution do you
   propose to optimize network consumption, reduce waiting time and avoid conflicts
   when refreshing the data?
- Parallelize API calls using coroutines to fetch data concurrently for multiple addresses, reducing waiting time.
- Use a mutex to avoid refresh conflicts.
- Additionally, implement caching mechanisms
- Using paging
- Fetch only new transactions using the `last_seen_txid` parameter for pagination.

2. A transaction could transition from the unconfirmed to the confirmed state. What strategy
   could be used to reduce the number of calls or the data exchanges with the esplora api?
- Cache transactions locally and fetch only new transactions using the `last_seen_txid` parameter for pagination.
- Periodically check the status of unconfirmed transactions.
- Using /address/:address/txs/mempool
- Using websockets if possible

3. A bitcoin address is a sensitive information that should remain private. Being able to
   associate it with a specific device should be avoided. Which strategy do you suggest to keep
   the address list safe?
- Encrypt the address list using platform-specific secure storage (Android Keystore, iOS Keychain).
- Avoid plain-text storage.

4. What could be used in place of an address list?
- Fetch addresses from a secure server using a wallet identifier.
- Use an extended public key (xpub) to derive addresses dynamically.

5. How do you suggest to mitigate man-in-the-middle attacks? (compatible with the esplora
   api documentation)
- Implement certificate pinning to verify the Esplora server’s identity.
- TLS configurations.

6. If esplora is hacked and it starts providing fake transactions, how can you realize it? What
   could you do to mitigate it?
- Perform sanity checks.
- Cross-verify with another explorer or node.