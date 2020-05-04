package com.wynk.test.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter

public class Item {

	private String itemId;
	private String itemName;
	private Integer bakeTime;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;

}
