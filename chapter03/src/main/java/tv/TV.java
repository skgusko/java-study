package tv;

public class TV {
	private int channel;
	private int volume;
	private boolean power;
	
	public TV() {
		this(7, 20, false);
	}
	
	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}

	public int getChannel() {
		return channel;
	}

	public int getVolume() {
		return volume;
	}

	public boolean isPower() {
		return power;
	}
	
	public void power(boolean power) {
		this.power = power;
	}
	
	public void channel(int channel) {
		if (channel > 255) {
			this.channel = 1;
		}
		else if (channel < 1) {
			this.channel = 255;
		}
		else {
			this.channel = channel;
		}
	}
	public void channel(boolean up) {
		if (up) {
			if (channel == 255) {
				channel = 1;
			}
			else {
				channel++;
			}
		}
		else {
			if (channel == 1) {
				channel = 255;
			}
			else {
				channel--;
			}
		}
	}
	
	public void volume(int volume) {
		if (volume > 100) {
			this.volume = 0;
		}
		else if (volume < 0) {
			this.volume = 100;
		}
		else {
			this.volume = volume;
		}
	}
	public void volume(boolean up) {
		if (up) {
			if (volume == 100) {
				volume = 0;
			}
			else {
				volume++;
			}
		}
		else {
			if (volume == 0) {
				volume = 100;
			}
			else {
				volume--;
			}
		}
	}
	
	public void status() {
		System.out.println("TV[channel=" + channel + 
				", volume=" + volume + 
				", power=" + (power ? "on" : "off") + "]");
	}
	
	
}

/*
[실행 결과]
7 20 off
7 0 on
7 100 on
255 100 on
3 100 on
3 100 off
*/