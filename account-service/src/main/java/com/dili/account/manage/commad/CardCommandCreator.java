package com.dili.account.manage.commad;

import com.dili.account.manage.ICardCommand;
import com.dili.ss.util.SpringUtil;

public class CardCommandCreator {
	
	private static CardCommandCreator instance = null;
	
	public static CardCommandCreator getInstance() {
		if (instance == null) {
			synchronized (CardCommandCreator.class) {
				if (instance == null) {
					instance = new CardCommandCreator();
				}
			}
		}
		return instance ;
	}
	
	public ICardCommand createCardCommand(CardCommandType cardCommandType) {
		return (ICardCommand) SpringUtil.getBean(cardCommandType.getName());
	}
	
}
