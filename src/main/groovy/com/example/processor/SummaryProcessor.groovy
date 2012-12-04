package com.example.processor

import groovy.util.logging.Slf4j

import org.springframework.batch.item.ItemProcessor

import com.example.domain.Summary

@Slf4j
class SummaryProcessor implements ItemProcessor<Long, Summary> {
	
	Summary process(Long id) {
		String threadName = Thread.currentThread().getName();
		Summary summaryToReturn = new Summary(id: id, description:"Test$id");
		log.info("Thread $threadName - processing $id")
		return summaryToReturn
		
	}

}
