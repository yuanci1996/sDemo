package com.nettyTest;

public class LuckMessage {

	private LuckHeader luckHeader;
	private String content;
	public LuckMessage(LuckHeader luckHeader,String content) {
		this.luckHeader=luckHeader;
		this.content=content;
	}
	public LuckHeader getLuckHeader() {
		return luckHeader;
	}
	public void setLuckHeader(LuckHeader luckHeader) {
		this.luckHeader = luckHeader;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "LuckMessage [luckHeader=" + luckHeader + ", content=" + content + "]";
	}
	
}
