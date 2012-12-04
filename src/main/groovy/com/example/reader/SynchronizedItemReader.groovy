package com.example.reader

import groovy.util.logging.Slf4j

import org.springframework.batch.item.ExecutionContext
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemStream
import org.springframework.batch.item.ItemStreamException

@Slf4j
class SynchronizingItemReader implements ItemReader<Long>, ItemStream {
	private ItemReader<Long> delegate;
        private final List<Long> processedAccounts = new ArrayList<Long>();
	public synchronized Long read() throws Exception {		
		String threadName = Thread.currentThread().getName();
		Long account = delegate.read();	
               if (account == null) {
			log.info("$threadName: - DONE");
		} else {
			if (!processedAccounts.contains(account)) {
				processedAccounts.add(account);	
			} else {
				throw new Exception("BAD");
			}
		}	
		return account;
	}

	public ItemReader<Long> getDelegate() {
		return delegate;
	}

	public void setDelegate(ItemReader<Long> delegate) {
		this.delegate = delegate;
	}

	// Stream
	
	public void close() throws ItemStreamException {
		if (this.delegate instanceof ItemStream) {
			((ItemStream)this.delegate).close();
		}
	}

	public void open(ExecutionContext context) throws ItemStreamException {
		if (this.delegate instanceof ItemStream) {
			((ItemStream)this.delegate).open(context);
		}
	}

	public void update(ExecutionContext context) throws ItemStreamException {
		if (this.delegate instanceof ItemStream) {
			((ItemStream)this.delegate).update(context);
		}
	}

}

