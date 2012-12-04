package com.example.writer


import groovy.util.logging.Slf4j

import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

import com.example.domain.Summary

@Slf4j
class SummaryItemWriter implements ItemWriter<Summary> {

	void write(List<? extends Summary> data) {
		log.info "Writing chunk: ${data.size()}"
	}
}
