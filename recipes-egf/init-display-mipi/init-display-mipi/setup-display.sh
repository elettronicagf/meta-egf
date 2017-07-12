setGpio 508 1
setGpio 507 1
setGpio 506 0
sleep 1
setGpio 506 1
i2cset -f -y 3 0x2d 0x09 0x00 b
i2cset -f -y 3 0x2d 0x0a 0x03 b                                                                                                                            
i2cset -f -y 3 0x2d 0x0b 0x28 b                                                                                      
i2cset -f -y 3 0x2d 0x0d 0x00 b  
i2cset -f -y 3 0x2d 0x10 0x36 b
i2cset -f -y 3 0x2d 0x11 0x00 b 
i2cset -f -y 3 0x2d 0x12 0x6c b                                                                            
i2cset -f -y 3 0x2d 0x18 0x78 b                                                                                      
i2cset -f -y 3 0x2d 0x19 0x00 b                                                                                       
i2cset -f -y 3 0x2d 0x1A 0x03 b                                                                                      
i2cset -f -y 3 0x2d 0x1b 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x20 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x21 0x04 b                                                                                      
i2cset -f -y 3 0x2d 0x22 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x23 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x24 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x25 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x26 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x27 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x28 0x20 b                                                                                      
i2cset -f -y 3 0x2d 0x29 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x2A 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x2B 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x2C 0x3c b                                                                                      
i2cset -f -y 3 0x2d 0x2D 0x00 b                                                           
i2cset -f -y 3 0x2d 0x30 0x0a b                                                                                      
i2cset -f -y 3 0x2d 0x31 0x00 b                                                           
i2cset -f -y 3 0x2d 0x34 0xa0 b                                      
i2cset -f -y 3 0x2d 0x35 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x36 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x37 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x38 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x39 0x00 b                                                                                      
i2cset -f -y 3 0x2d 0x3A 0x00 b                                                           
i2cset -f -y 3 0x2d 0x3C 0x00 b                                      
sleep 1                                                                                  
i2cset -f -y 3 0x2d 0x0d 0x01 b                                                                                      
i2cset -f -y 3 0x2d 0x09 0x01 b
