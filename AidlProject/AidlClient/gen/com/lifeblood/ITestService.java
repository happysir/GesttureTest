/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\workaspace\\AidlClient\\src\\com\\lifeblood\\ITestService.aidl
 */
package com.lifeblood;
// See the list above for which classes need
// import statements (hint--most of them)
// Declare the interface.

public interface ITestService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.lifeblood.ITestService
{
private static final java.lang.String DESCRIPTOR = "com.lifeblood.ITestService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.lifeblood.ITestService interface,
 * generating a proxy if needed.
 */
public static com.lifeblood.ITestService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.lifeblood.ITestService))) {
return ((com.lifeblood.ITestService)iin);
}
return new com.lifeblood.ITestService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getAccountBalance:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getAccountBalance();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setOwnerNames:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<java.lang.String> _arg0;
_arg0 = data.createStringArrayList();
this.setOwnerNames(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getCustomerList:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String[] _arg1;
int _arg1_length = data.readInt();
if ((_arg1_length<0)) {
_arg1 = null;
}
else {
_arg1 = new java.lang.String[_arg1_length];
}
int _result = this.getCustomerList(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(_result);
reply.writeStringArray(_arg1);
return true;
}
case TRANSACTION_showTest:
{
data.enforceInterface(DESCRIPTOR);
this.showTest();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.lifeblood.ITestService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
// Methods can take 0 or more parameters, and
// return a value or void.

@Override public int getAccountBalance() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAccountBalance, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setOwnerNames(java.util.List<java.lang.String> names) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStringList(names);
mRemote.transact(Stub.TRANSACTION_setOwnerNames, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Methods can even take other AIDL-defined parameters.
//BankAccount createAccount(in String name, int startingDeposit, in IAtmService atmService);
// All non-Java primitive parameters (e.g., int, bool, etc) require
// a directional tag indicating which way the data will go. Available
// values are in, out, inout. (Primitives are in by default, and cannot be otherwise).
// Limit the direction to what is truly needed, because marshalling parameters
// is expensive.

@Override public int getCustomerList(java.lang.String branch, java.lang.String[] customerList) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(branch);
if ((customerList==null)) {
_data.writeInt(-1);
}
else {
_data.writeInt(customerList.length);
}
mRemote.transact(Stub.TRANSACTION_getCustomerList, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
_reply.readStringArray(customerList);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void showTest() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_showTest, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getAccountBalance = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_setOwnerNames = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getCustomerList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_showTest = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
// Methods can take 0 or more parameters, and
// return a value or void.

public int getAccountBalance() throws android.os.RemoteException;
public void setOwnerNames(java.util.List<java.lang.String> names) throws android.os.RemoteException;
// Methods can even take other AIDL-defined parameters.
//BankAccount createAccount(in String name, int startingDeposit, in IAtmService atmService);
// All non-Java primitive parameters (e.g., int, bool, etc) require
// a directional tag indicating which way the data will go. Available
// values are in, out, inout. (Primitives are in by default, and cannot be otherwise).
// Limit the direction to what is truly needed, because marshalling parameters
// is expensive.

public int getCustomerList(java.lang.String branch, java.lang.String[] customerList) throws android.os.RemoteException;
public void showTest() throws android.os.RemoteException;
}
