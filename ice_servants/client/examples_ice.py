# -*- coding: utf-8 -*-
#
# Copyright (c) ZeroC, Inc. All rights reserved.
#
#
# Ice version 3.7.10
#
# <auto-generated>
#
# Generated from file `examples.ice'
#
# Warning: do not edit this file.
#
# </auto-generated>
#

from sys import version_info as _version_info_
import Ice, IcePy

# Start of module Demo
_M_Demo = Ice.openModule('Demo')
__name__ = 'Demo'

_M_Demo._t_DedicatedService = IcePy.defineValue('::Demo::DedicatedService', Ice.Value, -1, (), False, True, None, ())

if 'DedicatedServicePrx' not in _M_Demo.__dict__:
    _M_Demo.DedicatedServicePrx = Ice.createTempClass()
    class DedicatedServicePrx(Ice.ObjectPrx):

        def sayHello(self, name, context=None):
            return _M_Demo.DedicatedService._op_sayHello.invoke(self, ((name, ), context))

        def sayHelloAsync(self, name, context=None):
            return _M_Demo.DedicatedService._op_sayHello.invokeAsync(self, ((name, ), context))

        def begin_sayHello(self, name, _response=None, _ex=None, _sent=None, context=None):
            return _M_Demo.DedicatedService._op_sayHello.begin(self, ((name, ), _response, _ex, _sent, context))

        def end_sayHello(self, _r):
            return _M_Demo.DedicatedService._op_sayHello.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_Demo.DedicatedServicePrx.ice_checkedCast(proxy, '::Demo::DedicatedService', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_Demo.DedicatedServicePrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::Demo::DedicatedService'
    _M_Demo._t_DedicatedServicePrx = IcePy.defineProxy('::Demo::DedicatedService', DedicatedServicePrx)

    _M_Demo.DedicatedServicePrx = DedicatedServicePrx
    del DedicatedServicePrx

    _M_Demo.DedicatedService = Ice.createTempClass()
    class DedicatedService(Ice.Object):

        def ice_ids(self, current=None):
            return ('::Demo::DedicatedService', '::Ice::Object')

        def ice_id(self, current=None):
            return '::Demo::DedicatedService'

        @staticmethod
        def ice_staticId():
            return '::Demo::DedicatedService'

        def sayHello(self, name, current=None):
            raise NotImplementedError("servant method 'sayHello' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_Demo._t_DedicatedServiceDisp)

        __repr__ = __str__

    _M_Demo._t_DedicatedServiceDisp = IcePy.defineClass('::Demo::DedicatedService', DedicatedService, (), None, ())
    DedicatedService._ice_type = _M_Demo._t_DedicatedServiceDisp

    DedicatedService._op_sayHello = IcePy.Operation('sayHello', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_string, False, 0),), (), ((), IcePy._t_string, False, 0), ())

    _M_Demo.DedicatedService = DedicatedService
    del DedicatedService

_M_Demo._t_SharedService = IcePy.defineValue('::Demo::SharedService', Ice.Value, -1, (), False, True, None, ())

if 'SharedServicePrx' not in _M_Demo.__dict__:
    _M_Demo.SharedServicePrx = Ice.createTempClass()
    class SharedServicePrx(Ice.ObjectPrx):

        def getTime(self, context=None):
            return _M_Demo.SharedService._op_getTime.invoke(self, ((), context))

        def getTimeAsync(self, context=None):
            return _M_Demo.SharedService._op_getTime.invokeAsync(self, ((), context))

        def begin_getTime(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_Demo.SharedService._op_getTime.begin(self, ((), _response, _ex, _sent, context))

        def end_getTime(self, _r):
            return _M_Demo.SharedService._op_getTime.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_Demo.SharedServicePrx.ice_checkedCast(proxy, '::Demo::SharedService', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_Demo.SharedServicePrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::Demo::SharedService'
    _M_Demo._t_SharedServicePrx = IcePy.defineProxy('::Demo::SharedService', SharedServicePrx)

    _M_Demo.SharedServicePrx = SharedServicePrx
    del SharedServicePrx

    _M_Demo.SharedService = Ice.createTempClass()
    class SharedService(Ice.Object):

        def ice_ids(self, current=None):
            return ('::Demo::SharedService', '::Ice::Object')

        def ice_id(self, current=None):
            return '::Demo::SharedService'

        @staticmethod
        def ice_staticId():
            return '::Demo::SharedService'

        def getTime(self, current=None):
            raise NotImplementedError("servant method 'getTime' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_Demo._t_SharedServiceDisp)

        __repr__ = __str__

    _M_Demo._t_SharedServiceDisp = IcePy.defineClass('::Demo::SharedService', SharedService, (), None, ())
    SharedService._ice_type = _M_Demo._t_SharedServiceDisp

    SharedService._op_getTime = IcePy.Operation('getTime', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (), (), ((), IcePy._t_string, False, 0), ())

    _M_Demo.SharedService = SharedService
    del SharedService

# End of module Demo
