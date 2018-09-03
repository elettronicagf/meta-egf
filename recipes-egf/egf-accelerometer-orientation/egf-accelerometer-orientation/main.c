#include "i2c-dev.h"
#include "string.h"
#include "stdio.h"
#include <fcntl.h>
#include <unistd.h>

void readAccelerometer(int accelerometerFd)
{
    if(accelerometerFd>=0){
        int val= i2c_smbus_read_byte_data(accelerometerFd, 0x10);
        int mPLFlag = (val>>1) & 0x3;

        switch (mPLFlag){
        case 0:
        	printf("Portrait Up\n");
        	break;
        case 1:
        	printf("Portrait Down\n");
                break;
        case 2:
        	printf("Landscape Right\n");
        	break;
        case 3:
        	printf("Landscape Left\n");
       	 	break;
    	default:
        	break;
    	}
    }
}



int main(int argc, char **argv) {
	int mAccelerometerFD;

	mAccelerometerFD = open("/dev/i2c-0", O_RDWR);
	if(mAccelerometerFD >= 0){
		int ret;
		if (ioctl(mAccelerometerFD, I2C_SLAVE_FORCE, 0x1d) < 0) {
		        close(mAccelerometerFD);
		        mAccelerometerFD = -1;
			return -1;
		    }
		/*put stand by*/
		ret=i2c_smbus_write_byte_data(mAccelerometerFD,0x2A,0x00);
		if (ret) {
		   mAccelerometerFD = -1;
		}
		/*set frequence 12.5Hz*/
		ret=i2c_smbus_write_byte_data(mAccelerometerFD,0x2A,0x28);
		if (ret) {
		   mAccelerometerFD = -1;
		}
		/*enable P/L detection*/
		ret=i2c_smbus_write_byte_data(mAccelerometerFD,0x11,0xC0);
		if (ret) {
		   mAccelerometerFD = -1;
		}
		/*put active*/
		ret=i2c_smbus_write_byte_data(mAccelerometerFD,0x2a,0x29);
		if (ret) {
		   mAccelerometerFD = -1;
		}
	}
	sleep(1);
	readAccelerometer(mAccelerometerFD);
	close(mAccelerometerFD);
	return 0;

}
