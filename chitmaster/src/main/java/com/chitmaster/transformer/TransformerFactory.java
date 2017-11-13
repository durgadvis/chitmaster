package com.chitmaster.transformer;

import com.chitmaster.dto.DtoType;

public class TransformerFactory {

	public Transformer getTransformer(DtoType dtoType) {
		
//		if (dtoType.equals(DtoType.CHIT_GROUP)) {
//			
//		}
		return new ChitGroupTransformer();
	}
}
