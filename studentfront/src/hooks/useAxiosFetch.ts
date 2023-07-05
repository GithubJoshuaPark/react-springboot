import { useState, useEffect } from 'react'
import axios from 'axios'

const useAxiosFetch = (dataUrl: string) => {
  const [data, setData] = useState([])
  const [isLoading, setIsLoading] = useState(false)
  const [fetchError, setFetchError] = useState(null)

  useEffect(() => {
    let isMounted = true;
    const source = axios.CancelToken.source(); // for cancelling axios request

    const fetchData = async (url: string) => {
      setIsLoading(true);
      try {
        const response = await axios.get(url, {
          cancelToken: source.token
        });

        if(isMounted) {
          setData(response.data)
          setFetchError(null);
        }

      } catch (error: any) {
        if(isMounted) {
          setData([]);
          setFetchError(error.message);
        }
      } finally {
         isMounted && setIsLoading(false);
      }
    }

    fetchData(dataUrl)
    const cleanUp = () => {
      isMounted = false;
      source.cancel(); // cancel axios request
    }
     return cleanUp;;
  }, [dataUrl]);

  return { data, isLoading, fetchError }
};

export default useAxiosFetch;