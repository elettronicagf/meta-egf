DEFINES  = -D __KERNEL_STRICT_NAMES 
PROGS_O  = main.o
PROG    = accelerometerOrientation
all:    $(PROG)

$(PROG): objs
	$(CC) $(CFLAGS) $(LFLAGS) -o accelerometerOrientation main.o $(LIBS)
xd:  xd.o
	gcc  -o xd xd.o 
xd.o:  xd.c
	gcc  -c xd.c 
objs:   $(PROGS_O)


.c.o:
	$(CC) $(CFLAGS)  -c -o $*.o $< 

.o:
	$(CXX) $(CFLAGS) $(LFLAGS) -o $* $(PROGS_O) $(LIBS)

clean:       cleanbin
	rm -f *.o *~ 

cleanbin:
	rm -f $(PROG)

